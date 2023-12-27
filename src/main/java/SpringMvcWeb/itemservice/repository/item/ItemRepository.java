package SpringMvcWeb.itemservice.repository.item;

import SpringMvcWeb.itemservice.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository //Component Scan 의 대상이 된다.
public class ItemRepository {
    private static final Map<Long, Item> store = new ConcurrentHashMap<>(); //static
    private static long sequence = 0; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId , Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    public void clearStroe() {
        store.clear();
    }
}
