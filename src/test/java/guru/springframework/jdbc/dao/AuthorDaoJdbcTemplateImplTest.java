package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by sergei on 26/02/2025
 */
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoJdbcTemplateImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    AuthorDaoJdbcTemplateImpl authorDao;

    @BeforeEach
    public void setUp() {
        authorDao = new AuthorDaoJdbcTemplateImpl(jdbcTemplate);
    }

    @Test
    public void testGetAll() {

        List<Author> authorList = authorDao.findAll(Pageable.ofSize(10));

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isGreaterThan(3);
    }

    @Test
    public void TestGetAuthorsByLastName_SortByFirstName() {

        int pageSize = 10;


        Sort sort = Sort.by(Sort.Direction.ASC, "first_name");

        Pageable pageable = PageRequest.of(0, pageSize, sort);

        List<Author> authorList = authorDao.findAllByLastNameSortByFirstName("Smith", pageable);

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(pageSize);
    }

    @Test
    public void TestGetAuthorsByLastName_SortByFirstName_DefaultIsAsc_Page1() {

        int pageSize = 10;

        Pageable pageable = PageRequest.of(0, pageSize);

        List<Author> authorList = authorDao.findAllByLastNameSortByFirstName("Smith", pageable);

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(pageSize);

        assertThat(authorList.get(0).getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    public void TestGetAuthorsByLastName_SortByFirstName_Page2() {

        int pageSize = 10;

        Pageable pageable = PageRequest.of(1, pageSize);

        List<Author> authorList = authorDao.findAllByLastNameSortByFirstName("Smith", pageable);

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(pageSize);

        assertThat(authorList.get(0).getFirstName()).isEqualTo("Dinesh");
    }

    @Test
    public void TestGetAuthorsByLastName_SortByFirstName_Page1_Desc() {

        int pageSize = 10;

        Sort sort = Sort.by(Sort.Direction.DESC, "firstName");

        Pageable pageable = PageRequest.of(0, pageSize, sort);

        List<Author> authorList = authorDao.findAllByLastNameSortByFirstName("Smith", pageable);

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(pageSize);

        assertThat(authorList.get(0).getFirstName()).isEqualTo("Yugal");
    }

    @Test
    public void TestGetAuthorsByLastName_SortByFirstName_Desc_Page1_Limit40() {

        int pageSize = 40;

        Sort sort = Sort.by(Sort.Direction.DESC, "firstName");

        Pageable pageable = PageRequest.of(0, pageSize, sort);

        List<Author> authorList = authorDao.findAllByLastNameSortByFirstName("Smith", pageable);

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(pageSize);

        assertThat(authorList.get(0).getFirstName()).isEqualTo("Yugal");
    }

    @Test
    public void TestGetAuthors_Page1_Limit41() {

        int pageSize = 41;

        Sort sort = Sort.by(Sort.Direction.DESC, "firstName");

        Pageable pageable = PageRequest.of(0, pageSize, sort);

        List<Author> authorList = authorDao.findAll(pageable);

        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(pageSize);

    }
}
