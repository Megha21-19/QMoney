
package com.crio.warmup.stock;


import com.crio.warmup.stock.dto.AnnualizedReturn;

import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TotalReturnsDto;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;

import com.crio.warmup.stock.portfolio.PortfolioManagerImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {
  


  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Read the json file provided in the argument[0]. The file will be available in the classpath.
  //    1. Use #resolveFileFromResources to get actual file from classpath.
  //    2. Extract stock symbols from the json file with ObjectMapper provided by #getObjectMapper.
  //    3. Return the list of all symbols in the same order as provided in json.

  //  Note:
  //  1. There can be few unused imports, you will need to fix them to make the build pass.
  //  2. You can use "./gradlew build" to check if your code builds successfully.

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {
    String file = args[0];
    String contents = readFileAsString(file);
    ObjectMapper objectMapper = getObjectMapper();
    PortfolioTrade[] portfolioTrades = objectMapper.readValue(contents,PortfolioTrade[].class);
     List<String> result = new ArrayList<>();
     for(int i =0;i<portfolioTrades.length;i++){
      result.add(portfolioTrades[i].getSymbol());
     }
     return result;
    // return Stream.of(portfolioTrades).map(PortfolioTrade::getPurchaseDate).collect(Collectors.toList());
     
  }


  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.








  // TODO: CRIO_TASK_MODULE_REST_API
  //  Find out the closing price of each stock on the end_date and return the list
  //  of all symbols in ascending order by its close value on end date.



  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>







   



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(
        Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  private static String readFileAsString(String filename) throws URISyntaxException,IOException{
    return new String(Files.readAllBytes(resolveFileFromResources(filename).toPath()),
               "UTF-8");
  }


  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "/home/crio-user/workspace/m-megha2121-ME_QMONEY/qmoney/bin/main/trades.json";
     String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@2f9f7dcf";
     String functionNameFromTestFileInStackTrace = "PortfolioManagerApplicationTest.mainReadFile()";
     String lineNumberFromTestFileInStackTrace = "22:1";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }














  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Once you are done with the implementation inside PortfolioManagerImpl and
  //  PortfolioManagerFactory, create PortfolioManager using PortfolioManagerFactory.
  //  Refer to the code from previous modules to get the List<PortfolioTrades> and endDate, and
  //  call the newly implemented method in PortfolioManager to calculate the annualized returns.


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.



  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {
    ObjectMapper objectMapper = getObjectMapper();
    List<PortfolioTrade> trades = Arrays.asList(objectMapper.readValue(resolveFileFromResources(args[0]),
    PortfolioTrade[].class));
   List<TotalReturnsDto> sortedByValue = mainReadQuotesHelper(args,trades);
    Collections.sort(sortedByValue,TotalReturnsDto.closingComparator);
    List<String> stocks = new ArrayList<String>();
    for(TotalReturnsDto trd : sortedByValue){
      stocks.add(trd.getSymbol());
    }
    return stocks;

  }
  public static List<TotalReturnsDto> mainReadQuotesHelper(String[] args,
  List<PortfolioTrade> trades) throws IOException,URISyntaxException{
    RestTemplate restTemplate = new RestTemplate();
    List<TotalReturnsDto> tests = new ArrayList<TotalReturnsDto>();
    for (PortfolioTrade t : trades){
      String uri = "https://api.tiingo.com/tiingo/daily/" + t.getSymbol() + "/prices?startDate="
      + t.getPurchaseDate().toString() + "&endDate=" + args[1] + "&token=cfebbc0aef12670ab98533b6945f231424845a10";
      TiingoCandle[] results = restTemplate.getForObject(uri, TiingoCandle[].class);
       if (results != null){
         tests.add(new TotalReturnsDto(t.getSymbol(),results[results.length-1].getClose()));
     }
    }
  
    return tests;
  }









  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Now that you have the list of PortfolioTrade and their data, calculate annualized returns
  //  for the stocks provided in the Json.
  //  Use the function you just wrote #calculateAnnualizedReturns.
  //  Return the list of AnnualizedReturns sorted by annualizedReturns in descending order.

  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.

  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
        List<AnnualizedReturn> annualizedReturns = new ArrayList<>();
        LocalDate endLocalDate = LocalDate.parse(args[1]);
        File trades = resolveFileFromResources(args[0]);
        ObjectMapper objectMapper = getObjectMapper();
        PortfolioTrade[] tradeJsons = objectMapper.readValue(trades,PortfolioTrade[].class);
        for(int i=0;i<tradeJsons.length;i++){
          annualizedReturns.add(getAnnualizedReturn(tradeJsons[i],endLocalDate));
        
        }
        Comparator<AnnualizedReturn> SortByAnnReturn = Comparator.comparing(AnnualizedReturn::getAnnualizedReturn)
        .reversed();
        Collections.sort(annualizedReturns,SortByAnnReturn);
     return annualizedReturns;
  }
  

  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn

  private static AnnualizedReturn getAnnualizedReturn(PortfolioTrade trade, LocalDate endLocalDate) {
   String ticker = trade.getSymbol();
   LocalDate startLocalDate = trade.getPurchaseDate();
    if(startLocalDate.compareTo(endLocalDate)>=0){
           throw new RuntimeException();
    }
   String url = String.format("https://api.tiingo.com/tiingo/daily/%s/prices?" + "startDate=%s&endDate=%s&token=%s",
   ticker,startLocalDate.toString(), endLocalDate.toString(),TOKEN);
   RestTemplate restTemplate = new RestTemplate();
    TiingoCandle[] stocksStartToEndDate = restTemplate.getForObject(url,TiingoCandle[].class);
    if(stocksStartToEndDate!=null){
      TiingoCandle stockStartDate = stocksStartToEndDate[0];
    TiingoCandle stockLatest = stocksStartToEndDate[stocksStartToEndDate.length-1];
      Double buyPrice = stockStartDate.getOpen();
      Double sellPrice = stockLatest.getClose();
      AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(endLocalDate, trade, buyPrice, sellPrice);
     return annualizedReturn;
   }
   else{
     return new AnnualizedReturn(ticker,Double.NaN, Double.NaN);
   }

    }
    
  public static final String TOKEN = "cfebbc0aef12670ab98533b6945f231424845a10";

  public static AnnualizedReturn calculateAnnualizedReturns(
      LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        Double absReturn = (sellPrice-buyPrice)/ buyPrice;
        String symbol = trade.getSymbol();
        LocalDate purchaseDate = trade.getPurchaseDate();
        Double numYears = (double) ChronoUnit.DAYS.between(purchaseDate,endDate)/365;
        Double annualizedReturns = Math.pow((1+absReturn),(1/numYears)) -1;

      return new AnnualizedReturn(symbol,annualizedReturns,absReturn);
  }

  
   
 











  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
       String file = args[0];
       LocalDate endDate = LocalDate.parse(args[1]);
       String contents = readFileAsString(file);
       ObjectMapper objectMapper = getObjectMapper();
       PortfolioTrade[] portfolioTrades = objectMapper.readValue(contents,PortfolioTrade[].class);
       return portfolioManager.calculateAnnualizedReturn(Arrays.asList(portfolioTrades), endDate);
  }
   
  public static RestTemplate restTemplate = new RestTemplate();
  public static PortfolioManager portfolioManager = PortfolioManagerFactory.getPortfolioManager(restTemplate);



  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());



    printJsonObject(mainReadFile(args));



    printJsonObject(mainReadQuotes(args));

    //printJsonObject(mainReadQuotesHelper(args, null));

   




    printJsonObject(mainCalculateSingleReturn(args));


    printJsonObject(mainCalculateReturnsAfterRefactor(args));

  }
}

