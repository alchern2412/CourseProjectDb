package by.belstu.alchern.db.courseproject.controller.command;

import by.belstu.alchern.db.courseproject.controller.command.impl.*;
import by.belstu.alchern.db.courseproject.controller.command.impl.admin.*;
import by.belstu.alchern.db.courseproject.controller.command.impl.user.DeleteOrderCommand;
import by.belstu.alchern.db.courseproject.controller.command.impl.user.RequestOrderCommand;
import by.belstu.alchern.db.courseproject.controller.command.impl.user.UserOrdersCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    MAIN {
        {
            this.command = new MainCommand();
        }
    },
    EDIT_PROFILE {
        {
            this.command = new EditProfileCommand();
        }
    },
    FIND_FLIGHT {
        {
            this.command = new FindFlightCommand();
        }
    },
    REQUEST_ORDER {
        {
            this.command = new RequestOrderCommand();
        }
    },
    USER_ORDERS {
        {
            this.command = new UserOrdersCommand();
        }
    },
    DELETE_ORDER {
        {
            this.command = new DeleteOrderCommand();
        }
    },
    ALL_USERS {
        {
            this.command = new AllUsersCommand();
        }
    },
    ALL_FLIGHTS {
        {
            this.command = new AllFlightsCommand();
        }
    },
    CREATE_FLIGHT {
        {
            this.command = new CreateFlightCommand();
        }
    },
    EDIT_FLIGHT {
        {
            this.command = new EditFlightCommand();
        }
    },
    ALL_TICKETS {
        {
            this.command = new AllTicketsCommand();
        }
    },
    CONFIRM_TICKET {
        {
            this.command = new ConfirmTicketCommand();
        }
    },
    TRACK_FLIGHTS {
        {
            this.command = new TrackFlightCommand();
        }
    },
    EXPORT_IMPORT_XML {
        {
            this.command = new ExportImportXmlCommand();
        }
    },
    EXPORT_XML {
        {
            this.command = new ExportXmlCommand();
        }
    },
    IMPORT_XML {
        {
            this.command = new ImportXmlCommand();
        }
    }

    ;
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }

}
