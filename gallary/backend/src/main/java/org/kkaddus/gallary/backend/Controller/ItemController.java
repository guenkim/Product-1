package org.kkaddus.gallary.backend.Controller;

import org.kkaddus.gallary.backend.entity.Item;
import org.kkaddus.gallary.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }
}
