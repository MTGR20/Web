package ssu.swcontest2023.domain;

import javax.persistence.*;

// DB에 (일시적으로) 올라온 상품들을 뷰로 띄울 때 사용할 객체
// * price, link, img, ... 등 더 필요한 정보는 필드와 게터세터 추가하기.

@Entity
@Table(name = "test_tbl")
public class  Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}

