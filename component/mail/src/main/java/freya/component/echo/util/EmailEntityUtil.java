package freya.component.echo.util;

import freya.component.echo.entity.GenericSendEmailEntity;

public class EmailEntityUtil {
    public static boolean validEmailEntity(GenericSendEmailEntity entity) {
        if (entity.getFrom() == null) {
            return false;
        } else if (entity.getRecipient() == null) {
            return false;
        } else if (entity.getContent() == null) {
            return false;
        } else if (entity.getSubject() == null) {
            return false;
        }
        return true;
    }
}
