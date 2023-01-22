package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.linuxuzbekistan.dto.AttachDTO;
import uz.linuxuzbekistan.entity.AttachEntity;
import uz.linuxuzbekistan.repository.AttachRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
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
            attachDTO.setUrl(serverUrl+"/"+path);
            attachDTO.setId(attach.getId());
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
}
