package uz.linuxuzbekistan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.dto.ArticleCreateDTO;
import uz.linuxuzbekistan.repository.ArticleRepository;

@Service
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;

    public ResponseEntity create(ArticleCreateDTO articleCreate) {




return null;
    }
}
