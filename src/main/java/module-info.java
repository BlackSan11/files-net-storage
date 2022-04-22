module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.codec;

    opens ru.gb.file.net.storage to javafx.fxml;
    exports ru.gb.file.net.storage;
}
