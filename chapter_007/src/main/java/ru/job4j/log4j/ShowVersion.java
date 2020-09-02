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
 * </dl>
 */

public class ShowVersion {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("path.separator"));
    }
}
