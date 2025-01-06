package com.example.EmployeeManagement.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GridFSService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    // Method to upload the proof file to MongoDB GridFS and return the file ID
    public String uploadProof(MultipartFile proofFile) throws IOException {
        // Upload the file to GridFS and get the file ID
        var fileId = gridFsTemplate.store(proofFile.getInputStream(), proofFile.getOriginalFilename(), proofFile.getContentType());

        // Return the file ID as a string
        return fileId.toString();
    }

    // Method to retrieve the file from GridFS
    public GridFsResource getFile(String fileId) {
        return gridFsTemplate.getResource(fileId);
    }

    // Method to get the URL of the file stored in GridFS
    public String getFileUrl(String fileId) {
        return "/files/" + fileId;  // URL for file retrieval via your controller
    }
}


