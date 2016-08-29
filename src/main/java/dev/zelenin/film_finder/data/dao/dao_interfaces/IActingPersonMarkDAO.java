package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.users.Client;

import java.util.List;

/**
 * Created by victor on 04.08.16.
 */
public interface IActingPersonMarkDAO extends IDAO<ActingPersonMark> {
    List<ActingPersonMark> findActingPersonMarksByClient(Client client);

    List<ActingPersonMark> findActingPersonMarksByActingPerson(ActingPerson person);
}
