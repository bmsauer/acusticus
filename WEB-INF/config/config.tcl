#!/usr/bin/env tclsh
package require Tcl 8.5
package require inifile 0.3

#config.tcl is a script to generate .ini config files
#based on environment.  It pulls variables from
#system environment variables.

set fileout [::ini::open config.ini w]

foreach {name value} [array get ::env] {
    if {[string match ACUSTICUS_* $name]} then {
	::ini::set $fileout acusticus $name $value
    } else {}
}

::ini::commit $fileout
::ini::close $fileout
puts "wrote config file"
