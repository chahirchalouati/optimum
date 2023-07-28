package com.crcl.core.helper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FileHelper Tests")
public class FileHelperTest {
    @Mock
    ResourceLoader resourceLoader;

    @InjectMocks
    FileHelper fileHelper;

    @Test
    @DisplayName("Load file content as string - Success")
    void loadFileAsString_Success() {
        String fileName = "testFile.txt";
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile.txt");

        String fileContent = fileHelper.loadFileAsString(fileName);

        Assertions.assertThat("This is a test file.").isEqualTo(fileContent);
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile.txt"));
    }

    @Test
    @DisplayName("Load file content as bytes - Success")
    void loadFileAsBytes_Success() {
        String fileName = "testFile.txt";
        byte[] expectedBytes = "This is a test file.".getBytes();
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile.txt");

        byte[] fileBytes = fileHelper.loadFileAsBytes(fileName);

        assertArrayEquals(expectedBytes, fileBytes);
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile.txt"));
    }

    @Test
    @DisplayName("Load file content as bytes - File not found")
    void loadFileAsBytes_FileNotFound() {
        String fileName = "testFile1.txt";
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile1.txt");

        byte[] fileBytes = fileHelper.loadFileAsBytes(fileName);

        Assertions.assertThat(fileBytes).isNull();
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile1.txt"));
    }

    @Test
    @DisplayName("Load buffered file content as string - Success")
    void loadBufferedFileAsString_Success() {
        String fileName = "testFile.txt";
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile.txt");

        String fileContent = fileHelper.loadBufferedFileAsString(fileName);

        Assertions.assertThat("This is a test file.").isEqualTo(fileContent);
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile.txt"));
    }

    @Test
    @DisplayName("Load buffered file content as string - File not found")
    void loadBufferedFileAsString_FileNotFound() {
        String fileName = "testFile1.txt";
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile1.txt");

        String fileContent = fileHelper.loadBufferedFileAsString(fileName);

        Assertions.assertThat(fileContent).isNull();
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile1.txt"));
    }

    @Test
    @DisplayName("Load buffered file content as bytes - Success")
    void loadBufferedFileAsBytes_Success() {
        String fileName = "testFile.txt";
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile.txt");

        byte[] fileBytes = fileHelper.loadBufferedFileAsBytes(fileName);

        Assertions.assertThat(fileBytes).isNotNull();
        Assertions.assertThat(fileBytes.length).isEqualTo(20);
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile.txt"));
    }

    @Test
    @DisplayName("Load buffered file content as bytes - File not found")
    void loadBufferedFileAsBytes_FileNotFound() {
        String fileName = "testFile1.txt";
        doReturn(new ClassPathResource(fileName))
                .when(resourceLoader).getResource("classpath:testFile1.txt");

        byte[] fileBytes = fileHelper.loadBufferedFileAsBytes(fileName);

        Assertions.assertThat(fileBytes).isNull();
        verify(resourceLoader, times(1)).getResource(eq("classpath:testFile1.txt"));
    }
}
