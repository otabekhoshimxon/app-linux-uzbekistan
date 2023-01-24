package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.linuxuzbekistan.dto.AttachDTO;
import uz.linuxuzbekistan.entity.AttachEntity;
import uz.linuxuzbekistan.repository.AttachRepository;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachService {


    @Autowired
    private AttachRepository attachRepository;

    @Value("${attach.folder}")
    private String attachFolder;


    public AttachEntity getAttachById(String id) {
        return attachRepository.findById(id).orElse(null);
    }

    public AttachDTO upload(MultipartFile multipartFile) {
        String serverUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String pathFolder = getYearMonthDateString();
        String extension = getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uuid = UUID.randomUUID().toString();
        String multipartFilename = uuid + "." + extension;
        File folder =new File(attachFolder+pathFolder);
        if (!folder.exists()){

            folder.mkdirs();
        }

        try{
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get( attachFolder+ pathFolder+"/"+multipartFilename);
            Files.write(path, bytes);

            AttachEntity attach=new AttachEntity();
            attach.setSize(multipartFile.getSize());
            attach.setPath(path.toString());
            attach.setOriginName(multipartFile.getOriginalFilename());
            attach.setExtention(extension);
            attach.setId(uuid);
            attachRepository.save(attach);

            AttachDTO attachDTO=new AttachDTO();
            attachDTO.setUrl(serverUrl+"/api/v1/attach/download/"+attach.getId());

            return attachDTO;





        }catch (Exception e){
            e.printStackTrace();
        }

       return  null;

    }

    public String getYearMonthDateString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day;
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public ResponseEntity download(String id) {

        try {

            Optional<AttachEntity> byId = attachRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseEntity.badRequest().body("attach not found");
            }
            AttachEntity attach = byId.get();
            return  ResponseEntity.ok().header(
                            HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(attach.getOriginName())).contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                    .body(new FileUrlResource(attach.getPath()));


        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }







    }
}
