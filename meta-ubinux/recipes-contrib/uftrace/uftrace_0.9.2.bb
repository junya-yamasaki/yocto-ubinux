SUMMARY = "Trace and analyze execution of a program written in C/C++"
HOMEPAGE = "https://github.com/namhyung/uftrace"
BUGTRACKER = "https://github.com/namhyung/uftrace/issues"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "elfutils"
DEPENDS_append_libc-musl = " argp-standalone"

inherit autotools

# v0.9.2
SRCREV = "66fc1fb973f4a44aecd216541405ffe05196f11e"
SRC_URI = "git://github.com/namhyung/${BPN} \
           "
S = "${WORKDIR}/git"

LDFLAGS_append_libc-musl = " -largp"

def set_target_arch(d):
    import re
    arch = d.getVar('TARGET_ARCH')
    if re.match(r'i.86', arch, re.I):
        return 'i386'
    elif re.match('armeb', arch, re.I):
        return 'arm'
    elif re.match('aarch64_be', arch, re.I):
        return 'aarch64'
    else:
        return arch

EXTRA_UFTRACE_OECONF = "ARCH=${@set_target_arch(d)} \
                        with_elfutils=/use/libelf/from/sysroot"

do_configure() {
    ${S}/configure ${EXTRA_UFTRACE_OECONF}
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

COMPATIBLE_HOST = "(i.86|x86_64|aarch64|arm)"

# uftrace supports armv6 and above
COMPATIBLE_HOST_armv4 = 'null'
COMPATIBLE_HOST_armv5 = 'null'
