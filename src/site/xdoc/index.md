* Exist many logging implementations
* any library -- should :warning:? NOT force the -- use of a particular one :warning:
* Apache Commons Logging
  * == thin bridge or adapter between different logging implementations
  * uses for
    * library / uses Apache Commons Logging -- can be used with -- ANY logging implementation | runtime
    * :eye: application / uses Apache Commons Logging 
      * -- can change, WITHOUT recompiling, to -- different logging implementations :eye:
      * own application, is which -- initialize or terminate the -- underlying logging implementation
        * :warning: ALTHOUGH, many logging implementations initialise themselves automatically  :warning: