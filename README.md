# rtfc

util function help you Read The F\*\*king Code

## Usage

add following line to your project.clj

```
[rtfc "0.0.1"]
```

```
user=> (use 'rtfc.core)
nil
user=> (defn foo [] (println "in foo"))
#'user/foo
user=> (trace #'foo pprint-all)
#<core$trace$fn__1209$fn__1210 rtfc.core$trace$fn__1209$fn__1210@685ba21a>
user=> (foo)
#'user/foo got no args
in foo
return value for #'user/foo is:
nil

nil
user=> (untrace-all)
nil
user=> (foo)
in foo
nil
```

have fun

## License

Copyright © 2014 xudifsd

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
