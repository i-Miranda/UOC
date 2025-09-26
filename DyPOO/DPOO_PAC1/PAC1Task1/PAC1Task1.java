public class PAC1Task1 {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No data");
        } else {

            int min, max, sum;

            min = max = sum = Integer.parseInt(args[0]);

            for (int i = 1; i < args.length; ++i) {
                int val = Integer.parseInt(args[i]);
                if (min > val) min = val;
                if (max < val) max = val;
                sum += val;
            }

            double avg = (double) sum / args.length;

            System.out.println(min + "\t" + max + "\t" + avg);
        }
    }

}
