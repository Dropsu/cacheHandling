package ds.profileserver.cassandra.cassandrarepositories;

import ds.model.UserProfile;
import ds.model.UserProfileKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CassUserProfileRepository extends CassandraRepository<UserProfile, UserProfileKey> {

//  List<UserProfile> findByKeyFirstName(final String firstName);
//
//  List<UserProfile> findByKeyFirstNameAndKeyDateOfBirthGreaterThan(
//      final String firstName, final LocalDateTime dateOfBirth);

//  // Don't do this!!
//  @Query(allowFiltering = true)
//  List<UserProfile> findByLastName(final String lastName);

}