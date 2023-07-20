/* package ssu.swcontest2023;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ssu.swcontest2023.repository.MemoryProductRepository;
import ssu.swcontest2023.repository.ProductRepository;
import ssu.swcontest2023.sevice.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

// * 비정형화된 코드나 상황에 따라 구현 클래스를 변경해야할 경우 설정을 통해 스프링 빈으로 등록한다.
// e.g. 강의의 전제 중 하나였던 'DB는 아직 설정하지 않음'을 고려하여 나중에 변경될 상황을 생각.
//  -> only just 설정 파일(스프링 빈 등록부의 리턴부)만 수정해줌으로써 변경하여 사용할 수 있다.

@Configuration
public class SpringConfig {

    /*
    //@PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
 //*

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //스프링 빈 직접 등록
    @Bean
    public ProductService productService(){
        return new ProductService(productRepository());
    }

    //스프링 빈 직접 등록
    @Bean
    public ProductRepository productRepository(){
        return new MemoryProductRepository();
        //return new JpaProductRepository(em);
    }

    //Controller는 스프링이 관리하는 것이기 때문에 컴포넌트 스캔 - 오토 와이어 활용함
}
*/