#스케쥴러 사용 DB INSERT

- 메인 페이지 우측에는 KOBIS 일일 박스오피스 API를 사용하여 데이터를 DB에 저장했고 

    저장 시 SPRING SCHEDULER를 이용하여 매일 00시 02분에 저장이 되도록 구현했습니다. 
    
> BoxOfficeService
      
```java
    
  @Service
  public class BoxOfficeService {

  @Value("${movie.url}")
  private String dataUrl;

  @Autowired
  RESTApiBoxOffice restApiBoxOffice;

  @Autowired
  ApiBoxOffice apiBoxOffice;

  @Autowired
  BoxOfficeMapper boxOfficeMapper;

  public List<BoxOfficeVO> getBoxOfficeList(int cnt) {
    return apiBoxOffice.getBoxOfficeList(cnt);
  }

  public BoxOfficeResponseVO getRESTBoxOfficeList() {
    Map<String, Object> param = new HashMap<>();
    param.put("targetDt", "20230130");
    BoxOfficeResponseVO response = (BoxOfficeResponseVO) this.restApiBoxOffice.getData(dataUrl,
        BoxOfficeResponseVO.class, param);
    List<BoxOfficeVO> boxOfficeList = response.getBoxOfficeResult().getDailyBoxOfficeList();
    for (BoxOfficeVO boxOffice : boxOfficeList)
      boxOffice.setTargetDt("20230130");
    return response;
  }

  public int insertBoxOfficeList(List<BoxOfficeVO> boxOfficeList) {
    return this.boxOfficeMapper.insertBoxOfficeList(boxOfficeList);
  }

  public int insertBoxOffice(BoxOfficeVO boxOffice) {
    return this.boxOfficeMapper.insertBoxOffice(boxOffice);
  }

  public List<BoxOfficeVO> selectBoxOfficeList() {
    return this.boxOfficeMapper.selectBoxOfficeList();
  }

}
```



> ApiScheduler
```java
  @Component
  @Slf4j
  public class ApiScheduler {
    @Autowired
    private BoxOfficeService boxOfficeService;

    @Scheduled(cron="0 2 0 * * *")
    public void dailyBoxOffice() {
      List<BoxOfficeVO> boxOfficeList = boxOfficeService.getBoxOfficeList(1);
      int result = boxOfficeService.insertBoxOfficeList(boxOfficeList);
      log.debug("result=>{}",result);
    }
 }
```


> 상세 뷰입니다.
<img src="https://user-images.githubusercontent.com/115143371/216739893-d6aedb5d-dc2f-4703-abf2-cc5b3527141a.png"/></a>
