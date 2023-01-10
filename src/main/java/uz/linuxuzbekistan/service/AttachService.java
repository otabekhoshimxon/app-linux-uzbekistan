package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.entity.AttachEntity;
import uz.linuxuzbekistan.repository.AttachRepository;

@Service
public class AttachService {


    @Autowired
    private AttachRepository attachRepository;


    public AttachEntity getAttachById(String id) {
        return attachRepository.findById(id).orElse(null);
    }

}
