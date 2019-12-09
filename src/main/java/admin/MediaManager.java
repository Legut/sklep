package admin;

import util.ContextOperations;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 3)
@WebServlet("/admin/media-manager")
public class MediaManager extends HttpServlet {
    private static final String UPLOAD_DIRECTORY_WEB = "src\\main\\webapp\\assets\\images\\products\\";
    private static final String UPLOAD_DIRECTORY_TARGET = "target\\sklep-monopolowy24\\assets\\images\\products\\";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long counter = 0;
        int amountPerPage, currentPage;
        if(request.getParameter("amountPerPage")==null) {
            request.setAttribute("amountPerPage", 35);
        } else {
            amountPerPage = Integer.parseInt(request.getParameter("amountPerPage"));
            request.setAttribute("amountPerPage", amountPerPage);
        }

        if(request.getParameter("currentPage")==null) {
            request.setAttribute("currentPage", 1);
        } else {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage", currentPage);
        }

        String uploadPathTarget = ContextOperations.getPathToRoot(getServletContext().getRealPath("")) + UPLOAD_DIRECTORY_TARGET;

        File dir = new File(uploadPathTarget);
        if(dir.listFiles()!=null) {
            File[] files = dir.listFiles();
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
            String[] linksTab = new String[files.length];
            int i = 0;
            for (File file : files) {
                linksTab[i] = file.toURI().toString().substring(file.toURI().toString().lastIndexOf("/assets"));
                i++;
                counter++;
            }

            List<String> imageLinksList = Arrays.asList(linksTab);
            Collections.reverse(imageLinksList);
            request.setAttribute("imageLinksList", imageLinksList);
        } else {
            request.setAttribute("imageLinksList", null);
        }
        request.setAttribute("amountOfImages", counter);
        request.getRequestDispatcher("/WEB-INF/admin/media-manager.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPathTarget = ContextOperations.getPathToRoot(getServletContext().getRealPath("")) + UPLOAD_DIRECTORY_TARGET;
        String uploadPathWeb = ContextOperations.getPathToRoot(getServletContext().getRealPath("")) + UPLOAD_DIRECTORY_WEB;
        String resizeString = request.getParameter("resizeValue");
        String onlyResizedImage = request.getParameter("onlyResizedImage");
        int resizeValue;
        if(resizeString!=null){ resizeValue = Integer.parseInt(resizeString); } else { resizeValue = 0; }
        File uploadDirTarget = new File(uploadPathTarget);
        File uploadDirWeb = new File(uploadPathWeb);
        String fileName;
        if (!uploadDirTarget.exists()) uploadDirTarget.mkdir();
        if (!uploadDirWeb.exists()) uploadDirWeb.mkdir();
        if (!request.getParts().isEmpty()) {
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                if (fileName!=null && (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png"))) {
                    String outputImagePathTarget = uploadPathTarget + fileName;
                    String outputImagePathWeb = uploadPathWeb + fileName;

                    String[] pathPartsTarget = outputImagePathTarget.split("\\.", 2);
                    String[] pathPartsWeb = outputImagePathWeb.split("\\.", 2);

                    String outputImagePathResizedTarget;
                    String outputImagePathResizedWeb;
                    if(resizeValue!=0){
                        outputImagePathResizedTarget = pathPartsTarget[0] + "-resized-" + resizeValue + "." + pathPartsTarget[1];
                        outputImagePathResizedWeb = pathPartsWeb[0] + "-resized-" + resizeValue + "." + pathPartsWeb[1];
                    } else {
                        outputImagePathResizedTarget = pathPartsTarget[0] + "-resized." + pathPartsTarget[1];
                        outputImagePathResizedWeb = pathPartsWeb[0] + "-resized." + pathPartsWeb[1];
                    }
                    part.write(outputImagePathTarget);

                    BufferedImage temp = ImageIO.read(new File(outputImagePathTarget));
                    if(resizeValue!=0){
                        if(temp.getWidth()>resizeValue) {
                            resize(outputImagePathTarget, outputImagePathResizedTarget, resizeValue);
                        }
                    } else if(temp.getWidth()>1024) {
                        resize(outputImagePathTarget, outputImagePathResizedTarget, 1024);
                    }
                    if(onlyResizedImage!=null){
                        if(onlyResizedImage.equals("on") && temp.getWidth()>resizeValue) {
                            File fileToDelete = new File(outputImagePathTarget);
                            if (fileToDelete.delete()) {
                                //File has been deleted!
                            } else {
                                //Delete operation is failed
                            }
                        }
                    }

                    // Copy files from target to web
                    File source = new File(outputImagePathTarget);
                    File dest = new File(outputImagePathWeb);
                    File sourceResized = new File(outputImagePathResizedTarget);
                    File destResized = new File(outputImagePathResizedWeb);
                    try {
                        if(source.exists()){ Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING); }
                        if(sourceResized.exists()){ Files.copy(sourceResized.toPath(), destResized.toPath()); }
                    } catch (IOException e) {
                        System.out.println("Something went wrong during file copying; MediaManager.DoPost() error ->> " + e.getMessage());
                    }
                }
            }
        }
        doGet(request, response);
    }
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                try {
                    return new String(content.substring(content.indexOf("=") + 2, content.length() - 1).getBytes(), "utf8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void resize(String inputImagePath, String outputImagePath, int scaledWidth) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledHeight = (scaledWidth * inputImage.getHeight()) / inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
}
