package util;

public class ConstantValues {
    public static final String SHOPPING_CART_BEAN_SESION_KEY = "shoppingCart";
    public static int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1)
            pos = str.indexOf(substr, pos + 1);
        return pos;
    }
}
