package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the address book.
 */
public class AddPersonCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + "person"
            + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + "person" + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final PersonDescriptor toAdd;
    private Person createdEntity;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddPersonCommand(PersonDescriptor personDescriptor) {
        requireNonNull(personDescriptor);
        toAdd = personDescriptor;
    }

    @Override
    protected boolean alreadyExists(Model model) {
        return model.hasPerson(toAdd.getPerson(model.getAddressBook().getCounter()));
    }

    @Override
    protected void addEntity(Model model) {
        createdEntity = toAdd.getPerson(model.getAddressBook().getCounter());
        model.addPerson(createdEntity);
    }

    @Override
    protected String getSuccessMessage() {
        return MESSAGE_SUCCESS;
    }

    @Override
    protected String getDuplicateEntityMessage() {
        return MESSAGE_DUPLICATE_PERSON;
    }

    @Override
    protected String formatEntity() {
        return Messages.formatPerson(createdEntity);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPersonCommand)) {
            return false;
        }

        AddPersonCommand otherAddPersonCommand = (AddPersonCommand) other;
        return toAdd.equals(otherAddPersonCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

    public static class PersonDescriptor {
        private final Name name;
        private final Phone phone;
        private final Email email;
        private final Address address;
        private final Set<Tag> tags;

        public PersonDescriptor(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.tags = tags;
        }

        /**
         * Takes in a personId and returns the person object with its relevant personId.
         * @param personId
         * @return
         */
        public Person getPerson(int personId) {
            return new Person(
                    name, phone, email, address, personId, tags
            );
        }
    }
}