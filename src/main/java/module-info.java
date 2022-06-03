open module francefx {
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.ikonli.materialdesign;
    requires transitive org.kordamp.ikonli.core;

    requires jakarta.persistence;
    requires java.desktop;

    exports fr.univ_amu.iut.view.map;
    exports fr.univ_amu.iut.DAO.model;
    exports fr.univ_amu.iut;
    exports fr.univ_amu.iut.DAO;
    exports fr.univ_amu.iut.DAO.factory;
}