package pages;

import dao.GalleryDAO;
import dao.ProductDAO;
import objects.Gallery;
import objects.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ShowProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowProduct() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("id");
        try {
            long id = Long.parseLong(parameter);
            long gallery_id = 0;
            Product product = ProductDAO.getProduct(id);
            if (product != null) {
                request.setAttribute("name", product.getProduct_name());
                request.setAttribute("category", product.getCategory());
                request.setAttribute("quantity", product.getQuantity());
                request.setAttribute("price", product.getPrice());
                request.setAttribute("description", product.getDescription());
                Gallery gallery = GalleryDAO.getGallery(gallery_id);
                if (gallery != null) {
                    if(gallery.getPhoto_1() != null){
                        String photo_1 = "/assets/images/gallery/" + gallery_id + "/" + gallery.getPhoto_1();
                        request.setAttribute("photo_1", photo_1);
                    }
                    if(gallery.getPhoto_2() != null){
                        String photo_2 = "/assets/images/gallery/" + gallery_id + "/" + gallery.getPhoto_2();
                        request.setAttribute("photo_2", photo_2);
                    }
                    if(gallery.getPhoto_3() != null){
                        String photo_3 = "/assets/images/gallery/" + gallery_id + "/" + gallery.getPhoto_3();
                        request.setAttribute("photo_3", photo_3);
                    }
                    if(gallery.getPhoto_4() != null){
                        String photo_4 = "/assets/images/gallery/" + gallery_id + "/" + gallery.getPhoto_4();
                        request.setAttribute("photo_4", photo_4);
                    }
                }
                request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/pages/error-simple.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/WEB-INF/pages/error-simple.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}