package com.example.tp_transverse_memos.objects;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "memos")
public class MemoDTO
{
    @PrimaryKey(autoGenerate = true)
    public long courseId = 0;
    public String intitule;

    // Exemple d'attribut non pris en compte par Room :
    @Ignore
    public boolean selectionne;
    // Constructeur public vide (obligatoire si autre constructeur existant) :
    public MemoDTO() {}
    // Autre constructeur :
    public MemoDTO(String intitule)
    {
        this.intitule = intitule;
    }

    /**
     * Getter intitulé.
     * @return Intitulé du mémo
     */
    public String getIntitule()
    {
        return intitule;
    }
}
