package ssu.swcontest2023.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.repository.ProductRepository;

//import javax.transaction.Transactional;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductService {   //Rmv

    private final ProductRepository productRepository;

    @Autowired
    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * 상품 검색
     */
    public void searchList(List<Product> products){
        // * DB 데이터 전달받기 (전체(40개) 상품)
        //productRepository.saveList(products);
    }

    /**
     * 조회 (by Id)
     */
    // 안내를 위한 함수
    public Optional<Product> findOne(Long productId){
        return productRepository.findById(productId);
    }

}