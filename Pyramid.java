import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Pyramid extends BST
{
        //initialize global string that will store final path once it has been found
        private static String final_path;

        public static void main(String[]args) throws Exception {
            //read pyramid from input file
            File file =
                    new File("C:\\Users\\charl\\PyramidDescent\\src\\pyramid_sample_input.txt");
            Scanner sc = new Scanner(file);
            //get the target value from the first line of the input file
            String firstLine = sc.nextLine();
            //access the String value of the target
            String[] line_with_target = firstLine.split("\\s+");
            String target_string = line_with_target[1];

            //parse the string to get the integer value of the target
            int target = Integer.parseInt(target_string);

            //get each line of the pyramid one by one, where first row is the point of the pyramid with size 1,
            //second is the second row with size 2, and so on.
            String token;
            //initialize an arraylist to store all rows of the pyramid
            ArrayList<int[]> temps = new ArrayList<int[]>();
            int size_of_row = 1;
            //keep reading input from pyramid
            while (sc.hasNext()) {
                token = sc.nextLine();
                //parse the string, splitting it at each , character to get just the numerical values
                String[] split_token = token.split(",");
                //create an array of alk integers in each row. then add this array to the arraylist
                int[] temp = new int[size_of_row];
                //parse each array to convert string values to integer values
                for (int j = 0; j < size_of_row; j++) {
                    temp[j] = Integer.parseInt(split_token[j]);
                }
                //add these integer arrays to arraylist
                temps.add(temp);
                size_of_row++;
            }
            // System.out.println(temps.size());

            //call recursive helper with the indicies of the first (topmost) value in the pyramid. this is at
            //position 0 in the first array in our arraylist. curr_arraylist_ind represents the value of the current row
            //in the pyramid we are at, and curr_array_ind represents which position we are in within that row.
            getPathHelper(temps, 0, 0, target, 1, "");
            PrintWriter out = new PrintWriter(new FileWriter(
                    "C:\\Users\\charl\\PyramidDescent\\src\\pyramid_sample_output.txt"));

            //output final path to output file
            out.println(final_path);

            //close the file
            out.close();
            //print final path to console
            System.out.println(final_path);

        }

        //recursive helper method which takes our arraylist, current arraylist index, current array input, target,
        //product, and path as input, and will return the path whose product is the specified target
        private static void getPathHelper(ArrayList<int[]> temps, int curr_arraylist_ind, int curr_array_ind,
                                   int target, int product, String path) {
            //update the product
            product *= temps.get(curr_arraylist_ind)[curr_array_ind];
            //base case!! want to update our global variable ONLY when we have found a path where the target = product,
            // making sure that we are in the last row of the pyramid (otherwise this would not be a complete path)
            if(product == target && curr_arraylist_ind == temps.size()-1) {
                //set global string to the path we have found
                final_path=path;
                return;
            }
            else {
                ///make sure our indices do not go out of bounds
                if (curr_arraylist_ind < temps.size() - 1 && curr_array_ind < temps.get(curr_arraylist_ind).length ) {
                    //recurse on left and right children of current value in the pyramid, updating our path with either
                    //an "L" or an "R" respectively
                    getPathHelper(temps, curr_arraylist_ind + 1, curr_array_ind, target, product,
                            path+ "L");
                    getPathHelper(temps, curr_arraylist_ind + 1, curr_array_ind + 1, target,
                            product, path+ "R");
                }
            }
            return;
        }

}

