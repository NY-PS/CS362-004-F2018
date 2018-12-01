import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }



   String validScheme = "http://";
   String validHost = "www.gmail.com";
   String validPort = ":80";
   String validPath = "/path";
   String validQuery = "?refresh=true";

   public void testManualTest() {
      //You can use this function to implement your manual testing
      UrlValidator urlVal = new UrlValidator();
      System.out.println("TESTS: Running Manual Testing\n");


      System.out.println("\n\nTESTING VALID SCHEMES: EXPECTED TRUE");
      System.out.println(urlVal.isValid("http://www.gmail.com"));
      System.out.println(urlVal.isValid("https://www.gmail.com"));

      System.out.println("\n\nTESTING INVALID SCHEMES: EXPECTED FALSE");
      System.out.println(urlVal.isValid("://www.gmail.com"));
      System.out.println(urlVal.isValid("http:/www.gmail.com"));
      System.out.println(urlVal.isValid("ht://www.gmail.com"));
      System.out.println(urlVal.isValid("ccc://www.gmail.com"));
      System.out.println(urlVal.isValid("https:/www.gmail.com"));

      System.out.println("\n\nTESTING VALID SCHEMES, VALID HOSTS: EXPECTED TRUE");
      System.out.println(urlVal.isValid("www.gmail%.com"));
      System.out.println(urlVal.isValid("http://gmail.com"));
      System.out.println(urlVal.isValid("http://192.168.1.1"));

      System.out.println("\n\nTESTING INVALID HOSTS: EXPECTED FALSE");
      System.out.println(urlVal.isValid("http://.gmail.com"));
      System.out.println(urlVal.isValid("www.gmail"));
      System.out.println(urlVal.isValid("https://gmail"));

      System.out.println("\n\nTESTING VALID SCHEMES, VALID HOSTS, VALID PORTS: EXPECTED TRUE");
      System.out.println(urlVal.isValid("http://www.gmail.com"));
      System.out.println(urlVal.isValid("http://gmail.com:80"));
      System.out.println(urlVal.isValid("http://192.168.1.1:1445"));

      System.out.println("\n\nTESTING INVALID PORTS: EXPECTED FALSE");
      System.out.println(urlVal.isValid("http://www.gmail.com:bad43"));
      System.out.println(urlVal.isValid("http://gmail.com:242342234"));
      System.out.println(urlVal.isValid("http://192.168.1.1badtest"));

      System.out.println("\n\nTESTING VALID SCHEMES, VALID HOSTS, VALID PORTS, VALID PATHS: EXPECTED TRUE");
      System.out.println(urlVal.isValid("http://www.gmail.com:8080/path"));
      System.out.println(urlVal.isValid("http://gmail.com:80/path/"));
      System.out.println(urlVal.isValid("http://192.168.1.1:1445/1/2/3/4/"));

      System.out.println("\n\nTESTING INVALID PATHS: EXPECTED FALSE\n");
      System.out.println(urlVal.isValid("http://www.gmail.com:8080//oopspath"));
      System.out.println(urlVal.isValid("http://gmail.com:80////alsobadpath"));
      System.out.println(urlVal.isValid("http://192.168.1.1:1445/ path / test"));

      System.out.println("\n\nTESTING VALID SCHEMES, VALID HOSTS, VALID PORTS, VALID PATHS, QUERIES: EXPECTED TRUE");
      System.out.println(urlVal.isValid("http://www.gmail.com:8080/path?foo=bar"));
      System.out.println(urlVal.isValid("http://gmail.com:80/path?state=oregon&best=true"));
      System.out.println(urlVal.isValid("http://192.168.1.1:1445/1/2/3/4/?foo+bar=foobar"));

      System.out.println("\n\nTESTING INVALID QUERIES: EXPECTED FALSE");
      System.out.println(urlVal.isValid("http://www.gmail.com:8080/path ?s"));
      System.out.println(urlVal.isValid("http://gmail.com:80/path/.a/=b c"));
      System.out.println(urlVal.isValid("http://192.168.1.1:1445/1/2/3/4/ ?s"));

   }


   public void testYourFirstPartition(){
      //You can use this function to implement your First Partition testing     
      String[] validSchemesList = {"http://","https://","ftp://"};
      String[] invalidSchemesList = {"http:/","3ht://","http/", "http:", "://","///:"};
      UrlValidator urlVal = new UrlValidator();

      //TEST SCHEMES: VALID (TRY URLS CONTAINING THE VALID SCHEMES)
      System.out.println("\n\nTESTS: Running First Partition Testing (Valid Schemes)");
      for (int i = 0; i < validSchemesList.length; i++) {
         String testURL = validSchemesList[i] + validHost;
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validSchemesList[i] + validHost + validPort;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validSchemesList[i] + validHost + validPort + validPath;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validSchemesList[i] + validHost + validPort + validPath + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

      }

      //TEST SCHEMES: INVALID (TRY URLS CONTAINING THE INVALID SCHEMES)
      System.out.println("\n\nTESTS: Running First Partition Testing (Invalid Schemes)");
      for (int i = 0; i < invalidSchemesList.length; i++) {
         String testURL = invalidSchemesList[i] + validHost;
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = invalidSchemesList[i] + validHost + validPort;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = invalidSchemesList[i] + validHost + validPort + validPath;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = invalidSchemesList[i] + validHost + validPort + validPath + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }
      System.out.println("***********************\n\n");
   }

   public void testYourSecondPartition(){
      //You can use this function to implement your Second Partition testing     
      String[] validHostList = {"www.gmail.com","gmail.com","192.168.1.1"};
      String[] invalidHostList = {"www.gmail%.com",".gmail.com",""," ","badtest","gmail.."};
      UrlValidator urlVal = new UrlValidator();

      //TEST HOST PARTITIONING: VALID (TRY URLS CONTAINING THE VALID TEST HOSTS)
      System.out.println("\n\nTESTS: Running Second Partition Testing (Valid Host)\n");
      for (int i = 0; i < validHostList.length; i++) {
         String testURL = validScheme + validHostList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHostList[i] + validPort;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHostList[i] + validPort + validPath;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHostList[i] + validPort + validPath + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }

      //TEST HOST PARTITIONING: INVALID (TRY URLS CONTAINING THE INVALID TEST HOSTS)
      System.out.println("\n\nTESTS: Running Second Partition Testing (Invalid Host)\n");
      for (int i = 0; i < invalidHostList.length; i++) {
         String testURL = validScheme + invalidHostList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + invalidHostList[i] + validPort;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + invalidHostList[i] + validPort + validPath;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + invalidHostList[i] + validPort + validPath + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }
      System.out.println("***********************\n\n");
   }


   //You need to create more test cases for your Partitions if you need to 
   public void testYourThirdPartition(){
      String[] validPortList = {"",":80", ":8080", ":433", ":6789", ":562", ":539", ":1445", ":4384",":12455", ":65530"};
      String[] invalidPortList = {"aaa", ":bad43", ":242342234", ":-0"};
      UrlValidator urlVal = new UrlValidator();

      //TEST PORT PARTITIONING: VALID (TRY URLS CONTAINING THE VALID TEST PORTS)
      System.out.println("\n\nTESTS: Running Third Partition Testing (Valid Port)\n");
      for (int i = 0; i < validPortList.length; i++) {
         String testURL = validScheme + validHost + validPortList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHost + validPortList[i] + validPath;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHost + validPortList[i] + validPath + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }

      //TEST PORT PARTITIONING: INVALID (TRY URLS CONTAINING THE INVALID TEST PORTS)
      System.out.println("\n\nTESTS: Running Third Partition Testing (Invalid Port)\n");
      for (int i = 0; i < invalidPortList.length; i++) {
         String testURL = validScheme + validHost + invalidPortList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHost + invalidPortList[i] + validPath;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHost + invalidPortList[i] + validPath + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }
      System.out.println("***********************\n\n");
   }


   public void testYourFourthPartition(){
      String[] validPathList = {"", "/", "/path", "/path/", "/foo/bar", "/foo/bar/", "/1/2/3/4/" };
      String[] invalidPathList = {"/ path / test", "badpath", "////alsobadpath", "//oopspath"};
      UrlValidator urlVal = new UrlValidator();

      //TEST QUERY PARTITIONING: VALID (TRY URLS CONTAINING THE VALID TEST PATHS)
      System.out.println("\n\nTESTS: Running Fourth Partition Testing (Valid Path)\n");
      for (int i = 0; i < validPathList.length; i++) {
         String testURL = validScheme + validHost + validPort + validPathList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHost + validPort + validPathList[i] + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }

      //TEST QUERY PARTITIONING: INVALID (TRY URLS CONTAINING THE INVALID TEST PATHS)
      System.out.println("\n\nTESTS: Running Fourth Partition Testing (Invalid Path)\n");
      for (int i = 0; i < invalidPathList.length; i++) {
         String testURL = validScheme + validHost + validPort + invalidPathList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);

         testURL = validScheme + validHost + validPort + invalidPathList[i] + validQuery;
         isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }
      System.out.println("\n\n***********************\n\n");
   }

   public void testYourFifthPartition(){
      String[] validQueryList = {"", "?foo=bar", "?state=oregon&best=true", "?foo+bar=foobar"};
      String[] invalidQueryList = {"/ ?s", ".a/=b c", "?d e f", "!.com foo=bar"};
      UrlValidator urlVal = new UrlValidator();

      //TEST QUERY PARTITIONING: VALID (TRY URLS CONTAINING THE VALID TEST QUERIES)
      System.out.println("\n\nTESTS: Running Fifth Partition Testing (Valid Query)\n");
      for (int i = 0; i < validQueryList.length; i++) {
         String testURL = validScheme + validHost + validPort + validPath + validQueryList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }

      //TEST QUERY PARTITIONING: INVALID (TRY URLS CONTAINING THE INVALID TEST QUERIES)
      System.out.println("\n\nTESTS: Running Fifth Partition Testing (Invalid query)\n");
      for (int i = 0; i < invalidQueryList.length; i++) {
         String testURL = validScheme + validHost + validPort + validPath + invalidQueryList[i];
         boolean isVali = urlVal.isValid(testURL);
         System.out.println(isVali + " :: TESTING: " + testURL);
      }
      System.out.println("\n\n***********************\n\n");
   }

   private ResultPair[] resSchemes = {
           new ResultPair("http://", true),
           new ResultPair("https://", true),
           new ResultPair("ftp://", true),

           new ResultPair("http:/", false),
           new ResultPair("3ht://", false),
           new ResultPair("http/", false),
           new ResultPair("://", false),
           new ResultPair("///:", false)
   };

   private ResultPair[] resHost = {
           new ResultPair("www.gmail.com", true),
           new ResultPair("gmail.com", true),
           new ResultPair("192.168.1.1", true),

           new ResultPair("www.gmail%.com", false),
           new ResultPair("", false),
           new ResultPair(" ", false),
           new ResultPair("gmail..", false),
           new ResultPair(".gmail.com", false),
           new ResultPair("badtest", false)
   };

   private ResultPair[] resPorts = {
           new ResultPair("", true),
           new ResultPair(":80", true),
           new ResultPair(":8080", true),
           new ResultPair(":433", true),
           new ResultPair(":6789", true),
           new ResultPair(":562", true),
           new ResultPair(":539", true),
           new ResultPair(":1445", true),
           new ResultPair(":4384", true),
           new ResultPair(":12455", true),
           new ResultPair(":65530", true),

           new ResultPair("aaa", false),
           new ResultPair(":bad43", false),
           new ResultPair(":242342234", false),
           new ResultPair(":-0", false)
   };


   private ResultPair[] resPaths = {
           new ResultPair("", true),
           new ResultPair("/", true),
           new ResultPair("/path", true),
           new ResultPair("/path/", true),
           new ResultPair("/foo/bar", true),
           new ResultPair("/foo/bar/", true),
           new ResultPair("/1/2/3/4/", true),

           new ResultPair("/ path / test", false),
           new ResultPair("badpath", false),
           new ResultPair("////alsobadpath", false),
           new ResultPair("//oopspath", false)
   };


   private ResultPair[] resQueries = {
           new ResultPair("", true),
           new ResultPair("?foo=bar", true),
           new ResultPair("?state=oregon&best=true", true),
           new ResultPair("?foo+bar=foobar", true),

           new ResultPair("/ ?s", false),
           new ResultPair(".a/=b c", false),
           new ResultPair("?d e f", false),
           new ResultPair("!.com foo=bar", false)
   };


   private ResultPair[][] partsToCheck = {resSchemes, resHost, resPorts, resPaths, resQueries};

   public void testIsValid() {
      //You can use this function for programming based testing
      System.out.println("\n\nTESTS: Running PROGRAMMATICAL TESTING\n");

      UrlValidator urlVal = new UrlValidator();

      int countWronglyPassed = 0;
      int countWronglyFailed = 0;

      for (ResultPair scheme : resSchemes) {
         for (ResultPair host : resHost) {
            for (ResultPair port : resPorts) {
               for (ResultPair path : resPaths) {
                  for (ResultPair query : resQueries) {

                     String testURL = scheme.item + host.item + port.item + path.item + query.item;                  // Make the URL
                     boolean resExpected = scheme.valid && host.valid && port.valid && path.valid && query.valid;    // Expected result
                     boolean resActual = urlVal.isValid(testURL);                                                    // Actual returned result

                     if (resExpected != resActual) {
                        if (resExpected) {
                           countWronglyFailed++;
                           System.out.println("Wrong result (FALSE - NEG) from isValid with URL:  " + testURL);
                        }
                        else {
                           countWronglyPassed++;
                           System.out.println("Wrong result (FALSE - POS) from isValid with URL:  " + testURL);
                        }
                     }
                  }
               }
            }
         }
      }

      int totalURLsChecked = 1;

      for (ResultPair[] segments : partsToCheck) {
         totalURLsChecked *= segments.length;
      }

      int totalWrongAns = countWronglyPassed + countWronglyFailed;

      System.out.println("\n\nProgrammatical Test Result:\n");
      System.out.println(countWronglyFailed + " valid URLs were expected to passed, but failed instead.");
      System.out.println(countWronglyPassed + " invalid URLs wrongly passed, they should have failed.");
      System.out.println("Wrong Answers: " + totalWrongAns + " out of " + totalURLsChecked + ".");
   }
}