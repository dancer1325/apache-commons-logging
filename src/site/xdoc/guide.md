* Base abstractions
  * `Log`
  * `LogFactory`
* `Log`
  * := interface /
    * light-weight
    * -- independent to -- other logging toolkits
  * uses by
    * application developer -- to plug in -- ANY specific logging implementation
  * thin-wrapper Log implementations provided by JCL
    * [Log4j API](https://logging.apache.org/log4j/2.x/manual/api-separation)
    * [SLF4J](https://www.slf4j.org)
    * java.util.logging
* JCL
  * goal
    * unobtrusive as possible
  * if you add FULL "commons-logging.jar" | classpath -> JCL configured itself (NO configuration by you) / guess preferred logging system
  * if you have a DESIRED logging system -> provide a "commons-logging.properties" / specify the concrete logging library
    * -> ? JCL logs ONLY | that system ?
  * ? if NO particular logging library is specified -> JCL silently ignore ANY logging library / finds ?
    * == application run, ALTHOUGH a logging library can NOT be sued 
    * JCL configuration mechanisms / disable JCL's discovery process
      * allows
        * if a particular logging library can NOT be used -> ensure an exception
  * TODO:
* `LogFactory`
  * allows
    * creating `Log` instances
  * standard log factories / provided by JCL
    * `Log4jApiLogFactory`
      * used when
        * [Log4j API](https://logging.apache.org/log4j/2.x/manual/api-separation) | classpath & -- NOT redirected to -- SLF4J
      * ALL output -- is redirected to -> Log4j API
    * `Slf4jLogFactory`
      * used when
        * SLF4J | classpath 
      * ALL output -- is redirected to -> SLF4J
    * `LogFactoryImpl`
      * used when
        * previous cases are NOT valid
  * TODO: