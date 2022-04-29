package _HB_2.Backend;

import _HB_2.Backend.riderstop.RiderStop;
import _HB_2.Backend.riderstop.RiderStopController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class RiderStopControllerTests {

    List<RiderStop> listOfRiderStops;
    int tripId;
    int expectedLengthOfList;
    String expectedMessage;

    public RiderStopControllerTests(List<RiderStop> listOfRiderStops, int tripId, int expectedLengthOfList, String expectedMessage) {
        this.listOfRiderStops = listOfRiderStops;
        this.tripId = tripId;
        this.expectedLengthOfList = expectedLengthOfList;
        this.expectedMessage = expectedMessage;
    }

    @Parameters
//    public static Collection<Object[]> getParameters() throws FileNotFoundException {
        public static List<RiderStop> getParameters() throws FileNotFoundException {
//        Collection<Object[]> retlist = new ArrayList<>();
        List<RiderStop> retlist = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("src/test/java/RiderStopControllerTestsData.txt"));

            int numberOfRiderStopsInData = Integer.parseInt(in.nextLine());

            for (int i = 0; i < numberOfRiderStopsInData; i++) {
                //read in each riderStop
                String value = in.nextLine();
                String[] separatedValues = value.split(":");
                String riderDest = separatedValues[0];
                int riderId = Integer.parseInt(separatedValues[1]);
                String riderOrigin = separatedValues[2];
                int tripId = Integer.parseInt(separatedValues[3]);

                //make a riderStop
                RiderStop riderStop = new RiderStop();
                riderStop.setRiderId(riderId);
                riderStop.setRiderOriginAddress(riderOrigin);
                riderStop.setRiderDestAddress(riderDest);
                riderStop.setTripId(tripId);

                //add the riderStop to our data set
                retlist.add(riderStop);

            }
            //close the scanner
            in.close();

        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        return retlist;
    }

    @Test
    public void testNoRiderStops() throws Throwable {
        //Given

        //When

        //Then
    }



}
