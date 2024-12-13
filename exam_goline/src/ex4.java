public class ex4 {
    public static String formatPhoneNumber(String phoneNumber) {

        String digits = phoneNumber.replaceAll("\\D", "");


        if (digits.length() != 10) {
            throw new IllegalArgumentException("Số điện thoại phải chứa 10 chữ số.");
        }

        return String.format("(%s) %s-%s", digits.substring(0, 3), digits.substring(3, 6), digits.substring(6, 10));
    }

    public static void main(String[] args) {
        System.out.println(formatPhoneNumber("1234567890"));
    }

}
