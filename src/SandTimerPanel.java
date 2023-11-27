import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

class SandTimerPanel extends JPanel {
    private Image sandTimerImage;
    private Image sandImage;
    private Image sandLiesImage;
    private int sandLevel;
    private int sandSizeUp;
    private int sandSize;

    public SandTimerPanel() {
        try {
            sandTimerImage = ImageIO.read(new File("D:\\БГУИР\\ПрогСП\\лаба 6 сдать\\lab6new\\src\\sand_timer.png"));
            sandImage = ImageIO.read(new File("D:\\БГУИР\\ПрогСП\\лаба 6 сдать\\lab6new\\src\\sand.png"));
            sandLiesImage = ImageIO.read(new File("D:\\БГУИР\\ПрогСП\\лаба 6 сдать\\lab6new\\src\\sand_lies.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sandSize = sandLiesImage.getHeight(this)/9;
        sandSizeUp = sandLiesImage.getHeight(this)/5;
    }

    public void updateSandLevel() {
        sandLevel++;
        sandSize++;
        sandSizeUp--;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
//отображение песка вверху
        int newWidth3 = sandLiesImage.getWidth(this)/7 ;
        int newHeight3 = sandSizeUp ;

        int x2 = 20;
        int y2 = -50+newHeight3; // Сдвиг по y на высоту изображения
        g2d.drawImage(sandLiesImage, x2, y2, newWidth3, -newHeight3, this);

//отображение песка внизу
        int newWidth2 = sandLiesImage.getWidth(this)/9 ;
        int newHeight2 = sandSize ;

        g2d.drawImage(sandLiesImage, 30, 240, newWidth2, newHeight2, this);
        // Отображение изображения песочных часов
        int newWidth = sandTimerImage.getWidth(this) / 4;
        int newHeight = sandTimerImage.getHeight(this) / 4;

// Отображение уменьшенного изображения песочных часов
        g2d.drawImage(sandTimerImage, 0, 0, newWidth, newHeight, this);

        // Определение новых размеров изображения песчинок
        int newWidth1 = sandImage.getWidth(this) ;
        int newHeight1 = sandImage.getHeight(this) ;

// Отображение уменьшенного изображения песчинок
        int x = getWidth() / 4 - newWidth1 / 2;
        int y = getHeight() /2 - newHeight1  + sandLevel+40;
        g2d.drawImage(sandImage, x, y, newWidth1, newHeight1, this);


        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}