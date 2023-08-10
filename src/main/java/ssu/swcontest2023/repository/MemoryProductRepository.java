package ssu.swcontest2023.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ssu.swcontest2023.domain.Product;

import java.util.*;

@Repository
public class MemoryProductRepository  implements ProductRepository{

    private static Map<Long, Product> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        store.put(product.getId(), product);
        return product;
    }

    /*
    @Override
    public int saveList(List<Product> products) {
        return 0;
    }
     */

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null 반환 가능성 때문에 Optional로 감싸서 반환
    }


    @Override
    public Optional<Product> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //파라미터 name과 일치하는지 체크
                .findAny();                                         //하나라도
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}