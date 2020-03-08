public class FunnelChecker {

    public static boolean validation(String str1, String str2){
        return str1 != null && str2 != null && (str1.length() - str2.length() == 1);
    }

    public static boolean isFunnel(String str1, String str2) {
        if(!validation(str1, str2)){
            return false;
        }
        if(str1.length() == 1 && str2.equals("")){
            return true;
        }
        for (int i = 0; i < str1.length(); i++) {
            if ((str1.substring(0, i) + str1.substring(i + 1)).equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isFunnel("leave", "eave"));
        System.out.println(isFunnel("reset", "rest"));
        System.out.println(isFunnel("dragoon", "dragon"));
        System.out.println(isFunnel("eave", "leave"));
        System.out.println(isFunnel("sleet", "lets"));
        System.out.println(isFunnel("skiff", "ski"));
    }
}
