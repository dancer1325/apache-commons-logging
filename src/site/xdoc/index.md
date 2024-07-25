* Exist many logging implementations
* any library -- should ? NOT force the -- use of a particular one ?
* Apache Commons Logging
  * == thin bridge or adapter between different logging implementations
  * uses for
    * library / uses Apache Commons Logging -- can be used with -- ANY logging implementation | runtime
    * ? application / uses Apache Commons Logging 
      * -- can change, WITHOUT recompiling, to -- different logging implementations ?
      * own application, is which -- initialize or terminate the -- underlying logging implementation
        * ? ALTHOUGH, many logging implementations initialise themselves automatically  ?  