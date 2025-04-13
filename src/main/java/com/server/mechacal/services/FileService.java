package com.server.mechacal.services;

import java.io.IOException;
import java.io.InputStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String uploadFile(MultipartFile file) throws IOException 
    {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ObjectId fileId = gridFsTemplate.store(file.getInputStream(), filename, file.getContentType());
        return fileId.toHexString();
    }

    public InputStream getResourceFile(String fileId) throws IOException 
    {
        GridFSFile file = gridFsTemplate.findOne(query(where("_id").is(new ObjectId(fileId))));
        if (file == null) throw new IOException("File not found");
        GridFsResource resource = gridFsTemplate.getResource(file);
        return resource.getInputStream();
    }
    

    public String deleteFile(String fileId) 
    {
        gridFsTemplate.delete(query(where("_id").is(new ObjectId(fileId))));
        return "File deleted: " + fileId;
    }
}
