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
  * TODO: