DEPENDS += "readline8"
CPPFLAGS += "-I${STAGING_INCDIR}/readline8"
LDFLAGS += "-L${STAGING_LIBDIR}/readline8"

TARGET_CFLAGS_append = " -Wl,-rpath,${libdir}/readline8"

