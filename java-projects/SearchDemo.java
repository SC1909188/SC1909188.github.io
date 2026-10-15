public class SearchDemo {
    public static int linearSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] first = {8, 2, 5};
        int[] second = {4, 9};
        int[] third = {3, 3, 7};

        System.out.println(linearSearch(first, 2));
        System.out.println(linearSearch(second, 7));
        System.out.println(linearSearch(third, 3));
    }
}