package ssu.swcontest2023.domain;

import javax.persistence.*;

// DB에 (일시적으로) 올라온 상품들을 뷰로 띄울 때 사용할 객체
// * price, link, img, ... 등 더 필요한 정보는 필드와 게터세터 추가하기.

@Entity
@Table(name = "test_tbl")
public class  Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // generate identity?
    private Long id;            //`ranks`

    @Column(name="name")
    private String name;

    @Column(name="price")
    private String price;

    @Column(name="link")        //DEFAULT NULL
    private String link;

    @Column(name="main_picture")
    private String pic;

    @Column(name="src_link")    //DEFAULT NULL
    private String src_link;

    @Column(name="Allergy_extraction")
    private String allergy;


    public Product(){}
    public Product(Long id, String name, String price, String link, String pic, String src_link, String allergy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.link = link;
        this.pic = pic;
        this.src_link = src_link;
        this.allergy = allergy;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSrc_link() {
        return src_link;
    }

    public void setSrc_link(String src_link) {
        this.src_link = src_link;
    }


    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }


}

