package com.example.EmployeeManagement.Controller;
import com.mongodb.client.gridfs.GridFSFile;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.gridfs.GridFSFile;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    // Endpoint to fetch proof by file ID
    @GetMapping("/proof/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) throws IOException {
        // Convert string fileId to ObjectId
        ObjectId objectId = new ObjectId(fileId);

        // Fetch file from GridFS
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId)));

        if (gridFSFile == null) {
            return ResponseEntity.notFound().build();
        }

        // Open the file stream
        InputStream fileStream = gridFsTemplate.getResource(gridFSFile).getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Read the file and write it to byte array
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        byte[] fileData = byteArrayOutputStream.toByteArray();

        // Return the file as a response with correct headers
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + gridFSFile.getFilename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)  // Use appropriate media type, like PDF if needed
                .body(fileData);
    }
}
