package ru.job4j.log4j;

/**
 * System properties. The following properties are guaranteed to be defined:
 * <dl>
 * <dt>java.version         <dd>Java version number
 * <dt>java.version.date    <dd>Java version date
 * <dt>java.vendor          <dd>Java vendor specific string
 * <dt>java.vendor.url      <dd>Java vendor URL
 * <dt>java.vendor.version  <dd>Java vendor version
 * <dt>java.home            <dd>Java installation directory
 * <dt>java.class.version   <dd>Java class version number
 * <dt>java.class.path      <dd>Java classpath
 * <dt>os.name              <dd>Operating System Name
 * <dt>os.arch              <dd>Operating System Architecture
 * <dt>os.version           <dd>Operating System Version
 * <dt>file.separator       <dd>File separator ("/" on Unix)
 * <dt>path.separator       <dd>Path separator (":" on Unix)
 * <dt>line.separator       <dd>Line separator ("\n" on Unix)
 * <dt>user.name            <dd>User account name
 * <dt>user.home            <dd>User home directory
 * <dt>user.dir             <dd>User's current working directory
 * <dt>java.runtime.name    <dd>"Java(TM) SE Runtime Environment"
 * <dt>java.vm.version      <dd>"12.0.2+10"
 * <dt>sun.boot.library.path  <dd>"C:\Program Files\Java\jdk-12.0.2\bin"
 * <dt>maven.multiModuleProjectDirectory  <dd>"C:\projects\job4j\chapter_005"
 * <dt>java.vm.vendor       <dd>"Oracle Corporation"
 * <dt>java.vendor.url      <dd>"https://java.oracle.com/"
 * <dt>guice.disable.misplaced.annotation.check  <dd>"true"
 * <dt>java.vm.name         <dd>Java HotSpot(TM) 64-Bit Server VM"
 * <dt>sun.os.patch.level   <dd>""
 * <dt>user.script          <dd>""
 * <dt>user.country         <dd>"RU"
 * <dt>sun.java.launcher    <dd>"SUN_STANDARD"
 * <dt>java.vm.specification.name  <dd>"Java Virtual Machine Specification"
 * <dt>user.dir <dd>"C:\projects\job4j\chapter_005"
 * <dt>java.vm.compressedOopsMode <dd>"32-bit"
 * <dt>java.runtime.version <dd>"12.0.2+10"
 * <dt>java.awt.graphicsenv <dd>"sun.awt.Win32GraphicsEnvironment"
 * <dt>os.arch              <dd>"amd64"
 * <dt>java.io.tmpdir       <dd>"C:\Users\Alex\AppData\Local\Temp\"
 * <dt>line.separator       <dd>";"
 * <dt>java.vm.specification.vendor  <dd>"Oracle Corporation"
 * <dt>user.variant         <dd>""
 * <dt>os.name              <dd>"Windows 8.1"
 * <dt>maven.ext.class.path  <dd>"C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.3\plugins\maven\lib\maven-event-listener.jar"
 * <dt>classworlds.conf     <dd>"C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.3\plugins\maven\lib\maven3\bin\m2.conf"
 * <dt>sun.jnu.encoding     <dd>"Cp1251"
 * <dt>java.library.path    <dd>"C:\Program Files\Java\jdk-12.0.2\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Program Files (x86)\HP SimplePass\;C:\Program Files (x86)\AMD APP\bin\x86_64;C:\Program Files (x86)\AMD APP\bin\x86;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Windows Live\Shared;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;C:\Program Files (x86)\QuickTime\QTSystem\;C:\Program Files\Java\jdk-12.0.2\bin;C:\Tools\apache-maven-3.6.2\bin;%JAVA_HOME%\bin;%M2_HOME%\bin;C:\Program Files\PostgreSQL\12\bin;C:\Program Files\nodejs\;C:\Program Files\Git\cmd;;C:\Users\Alex\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\Alex\AppData\Roaming\npm;."
 * <dt>maven.conf           <dd>"C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.3\plugins\maven\lib\maven3/conf"
 * <dt>jdk.debug            <dd>"release"
 * <dt>java.class.version   <dd>"56.0"
 * <dt>java.specification.name  <dd>"Java Platform API Specification"
 * <dt>sun.management.compiler  <dd>"HotSpot 64-Bit Tiered Compilers"
 * <dt>os.version           <dd>"6.3"
 * <dt>user.home            <dd>"C:\Users\Alex"
 * <dt>user.timezone        <dd>"Europe/Moscow"
 * <dt>file.encoding        <dd>"UTF-8"
 * <dt>java.specification.version  <dd>"12"
 * <dt>user.name            <dd>"Alex"
 * <dt>idea.version2019.2.3  <dd>"true"
 * <dt>java.class.path      <dd>"C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.3\plugins\maven\lib\maven3\boot\plexus-classworlds-2.6.0.jar"
 * <dt>java.vm.specification.version  <dd>"12"
 * <dt>sun.arch.data.model  <dd>"64"
 * <dt>sun.java.command     <dd>"org.codehaus.classworlds.Launcher -Didea.version2019.2.3 test -P !test"
 * <dt>java.home            <dd>"C:\Program Files\Java\jdk-12.0.2"
 * <dt>user.language        <dd>"ru"
 * <dt>java.specification.vendor  <dd>"Oracle Corporation"
 * <dt>awt.toolkit          <dd>"sun.awt.windows.WToolkit"
 * <dt>java.vm.info         <dd>"mixed mode, sharing"
 * <dt>java.version         <dd>"12.0.2"
 * <dt>java.vendor          <dd>"Oracle Corporation"
 * <dt>maven.home           <dd>"C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.3\plugins\maven\lib\maven3"
 * <dt>file.separator       <dd>"\"
 * <dt>java.version.date    <dd>"2019-07-16"
 * <dt>java.vendor.url.bug  <dd>"https://bugreport.java.com/bugreport/"
 * <dt>sun.io.unicode.encoding  <dd>"UnicodeLittle"
 * <dt>sun.cpu.endian       <dd>"little"
 * <dt>sun.desktop          <dd>"windows"
 * <dt>sun.cpu.isalist      <dd>"amd64"
 * </dl>
 */

public class ShowVersion {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("path.separator"));
        System.out.println(System.getProperty("java.runtime.name")); //Java(TM) SE Runtime Environment
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("sun.desktop"));
    }
}
