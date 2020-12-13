package ru.job4j.synch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ParseFile {
    private static final Logger LOG = LoggerFactory.getLogger(ParseFile.class.getName());
    private File file;

    public synchronized void setFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                output.append((char) data);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = content.getBytes();
            bufferedOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
