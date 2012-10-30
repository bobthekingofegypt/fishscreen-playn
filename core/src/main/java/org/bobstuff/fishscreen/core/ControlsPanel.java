package org.bobstuff.fishscreen.core;

import playn.core.Font;
import playn.core.PlayN;
import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.bgs.BlankBackground;
import tripleplay.ui.layout.AbsoluteLayout;

/**
 * @author bob
 *
 */
public class ControlsPanel extends Group {
	
	private Label fpsLabel;
	private Label fishLabel;
	
	public ControlsPanel(final AquariumScreen delegate) {
		super(new AbsoluteLayout());
		
		this.addStyles(Style.BACKGROUND.is(new BlankBackground()));
		
		Styles bigLabel = Styles.make(
                Style.FONT.is(PlayN.graphics().createFont("Times New Roman", 
                								Font.Style.PLAIN, 32)),
                Style.HALIGN.center,
                Style.COLOR.is(0xffffffff));
		fpsLabel = new Label("");
        fpsLabel.addStyles(bigLabel);
        
        Label fpsDescriptionLabel = new Label("fps");
        fpsDescriptionLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.center);
        
        Label numberOfFishLabel = new Label("number of fish");
        numberOfFishLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.center);
        
		fishLabel = new Label("");
        fishLabel.addStyles(bigLabel);
        
        Label fishDescriptionLabel = new Label("fish");
        fishDescriptionLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.center);
        Button ten = new Button("10");
        ten.clicked().connect(new UnitSlot() { 
        	@Override
            public void onEmit () {
        		delegate.setNumberOfFish(10);
            }
        });
        
        Button hundred = new Button("100");
        hundred.clicked().connect(new UnitSlot() { 
        	@Override
            public void onEmit () {
        		delegate.setNumberOfFish(100);
            }
        });
        
        Button thousand = new Button("1000");
        thousand.clicked().connect(new UnitSlot() { 
        	@Override
            public void onEmit () {
        		delegate.setNumberOfFish(1000);
            }
        });
        
        this.add(AbsoluteLayout.at(fpsLabel, 0, 10, 100, 40));
        this.add(AbsoluteLayout.at(fpsDescriptionLabel, 0, 45, 100, 20));
        this.add(AbsoluteLayout.at(fishLabel, 80, 10, 100, 40));
        this.add(AbsoluteLayout.at(fishDescriptionLabel, 80, 45, 100, 20));
        this.add(AbsoluteLayout.at(numberOfFishLabel, 180, 10, 160, 20));
        this.add(AbsoluteLayout.at(ten, 180, 40, 40, 20));
        this.add(AbsoluteLayout.at(hundred, 240, 40, 40, 20));
        this.add(AbsoluteLayout.at(thousand, 300, 40, 40, 20));
	}
	
	public void setFps(int fps) {
		fpsLabel.text.update(String.valueOf(fps));
	}

	public void setNumberOfFish(int numberOfFish) {
		fishLabel.text.update(String.valueOf(numberOfFish));
	}
}
