package test.java.pomTest;

import main.java.dataHandlers.dataProvidershandler;
import main.java.runners.BaseTest;
import main.java.pom.Amazon.AmaoznHomepage;
import main.java.pom.Amazon.SearchPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static main.java.utils.Utils.snapshot;

public class Amaozontest extends BaseTest {

    @Test(dataProvider="AmazonTestData",groups = {"amazon"})
    public static void testCall(String testCase,String productName,String number) throws Exception {

// Web Driver initiliazations

        System.out.println("running for test data from data provider :- AmazonTestData:- "+testCase+productName+number);


        System.out.println("Executing test case ;- "+ testCase);

        AmaoznHomepage amaoznHomepage = new AmaoznHomepage();
        amaoznHomepage.launchurlAndSearch(productName);

        SearchPage searchPage = new SearchPage();
//        searchPage.selectGetItToday(oDriver);
        searchPage.selectObjectFromSearchList(number);
        searchPage.addToCart();
    }

    @DataProvider(name="AmazonTestData")
    public Object[][] testData() throws IOException {

        return dataProvidershandler.fileHandler("src/test/resources/testData/AmazonTestData.xlsx","search");
    }

}
