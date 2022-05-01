package _HB_2.Backend;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class TestingTests {

    int num1;
    int num2;
    boolean expected;

    public TestingTests(int num1, int num2, boolean expected) {
        this.num1 = num1;
        this.num2 = num2;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Collection<Object[]> retList = new ArrayList<Object[]>();
        try {
            Scanner in = new Scanner(new File("dataFile.txt"));

            // Read as many lines as there are in the file
            while (in.hasNextLine()) {
                String l = in.nextLine();

                // split the line using delimiter and then create the test-case object
                String dataArray[] = l.split(",");
                Object[] d = new Object[3];
                d[0] = Float.parseFloat(dataArray[0]);
                d[1] = Float.parseFloat(dataArray[1]);
                d[2] = Float.parseFloat(dataArray[2]);
                d[3] = Boolean.parseBoolean(dataArray[3]);
                d[4] = dataArray[4];

                // add the test data into the arraylist
                retList.add(d);

            } // end of while
            in.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        // return all the test-cases
        return retList;
    }

}
