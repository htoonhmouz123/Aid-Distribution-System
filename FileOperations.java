package fxProj;

import java.io.IOException;

public interface FileOperations {

    void SaveToTextFile(String fName) throws IOException;

    void LoadFromTextFile(String fName) throws IOException;

    void SaveToBinaryFile(String fName) throws IOException,ClassNotFoundException;

    void LoadFromBinaryFile(String fName) throws IOException,ClassNotFoundException;

}