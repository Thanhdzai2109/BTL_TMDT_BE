package org.sid.backendleDeal.web;

import org.apache.commons.io.IOUtils;
import org.sid.backendleDeal.dao.ShopRepository;
import org.sid.backendleDeal.entities.Category;
import org.sid.backendleDeal.entities.Offres;
import org.sid.backendleDeal.entities.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;
    @GetMapping(value="/listShop")
    public List<Shop> Listcategories(){
        return shopRepository.findAll();
    }
    @GetMapping(path="/photoShop/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
        Shop o = shopRepository.findById(id).get();
//    	File serverFile = new File("C:\\Users\\Boubaker\\Offress\\"+o.getPhotoName());
//        System.out.println("serverFile : " + serverFile);
//        return Files.readAllBytes(serverFile.toPath());
        try {
            File serverFile = new File("D:\\img\\Shop\\"+o.getPhotoName());
            InputStream in = new FileInputStream(serverFile);
            byte[] imageBytes = IOUtils.toByteArray(in);
            in.close();
            return imageBytes;
        } catch (IOException e) {
            return null;
        }
    }
    /* Web api REST Post UploadPhotoPourUnOffre dans un Dosier Offres et Enregistrer le nom du Photo Avec Spring DataRest */
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception {
        Shop o=shopRepository.findById(id).get();
        o.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"\\shop\\"+o.getPhotoName()),file.getBytes());
        shopRepository.save(o);
    }
//    @PostMapping(path = "/uploadphotoShop")
//    public void addPhotoTooffre(MultipartFile file, @PathVariable Long id) throws Exception {
//        Shop o=shopRepository.findById(id).get();
//        o.setPhotoName(file.getOriginalFilename());
//        Files.write(Paths.get(System.getProperty("user.home")+"\\shop\\"+o.getPhotoName()),file.getBytes());
//        shopRepository.save(o);
//    }
    @DeleteMapping(value="/shop/{id}")
    public void delete(@PathVariable(name="id") Long id) {
        shopRepository.deleteById(id);
    }
    @RequestMapping(value="/addshop",method=RequestMethod.POST)
    public Shop save(@RequestBody Shop o){
        return shopRepository.save(o);
    }

}
