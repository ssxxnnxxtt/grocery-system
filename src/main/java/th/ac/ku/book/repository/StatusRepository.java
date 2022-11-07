package th.ac.ku.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.ac.ku.book.model.Status;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT s FROM Status s WHERE s.statusID = 2 OR s.statusID = 3 OR s.statusID = 4 OR s.statusID = 5 OR s.statusID = 6")
    public List<Status> getApprove();

    @Query("SELECT s FROM Status s WHERE s.statusID = 3 OR s.statusID = 4 OR s.statusID = 5 OR s.statusID = 6")
    public List<Status> getPending();

    @Query("SELECT s FROM Status s WHERE s.statusID = 4 OR s.statusID = 5 OR s.statusID = 6")
    public List<Status> getPreparing();

    @Query("SELECT s FROM Status s WHERE s.statusID = 5 OR s.statusID = 6")
    public List<Status> getDelivering();

    @Query("SELECT s FROM Status s WHERE s.statusID = 6")
    public List<Status> getDelivered();
}
