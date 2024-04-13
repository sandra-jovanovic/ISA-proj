import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Centre } from 'src/app/models/centre.model';
import { CentresService } from 'src/app/services/centres.service';
import { MatLegacyTableDataSource as MatTableDataSource, MatLegacyTableModule as MatTableModule } from '@angular/material/legacy-table';
import {MatSort, Sort, MatSortModule} from '@angular/material/sort';
import {MatLegacyPaginator as MatPaginator} from '@angular/material/legacy-paginator';
import {appointmentSearchDTO} from "../../models/appointmentSearchDTO.model";
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import Point from 'ol/geom/Point';
import Feature from 'ol/Feature';
import { fromLonLat, transform } from 'ol/proj';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import { Overlay } from 'ol';
import Vector from 'ol/source/Vector';







@Component({
  selector: 'app-centres-list',
  templateUrl: './centres-list.component.html',
  styleUrls: ['./centres-list.component.css']
})
export class CentresListComponent implements AfterViewInit {

  displayedColumns: string[] = ['name', 'streetName', 'description', 'averageRate', 'Edit'];
  formDate:string="";
  formTime:string="";
  centres = new MatTableDataSource<Centre[]>;
  currentCentre: Centre = {};
  currentIndex = -1;
  streetName ='';
  name='';

  map!:Map;

  constructor(private centresService: CentresService) { }

  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  ngAfterViewInit(): void {
    this.retrieveCentres();
    this.centres.sort = this.sort;
    this.initializeMap();
  }


  initializeMap(){

    var content = document.getElementById('popup-content')!;
    //var center = transform([76.26, 9.93], 'EPSG:4326', 'EPSG:3857'); //initial position of map
        var view = new View({
          center: fromLonLat([20.448921, 44.786568]),
          zoom: 11,
        });

    //raster layer on map
       var OSMBaseLayer = new TileLayer({
            source: new OSM()
        });
    
     const straitSource = new Vector({ wrapX: true });
        var straitsLayer = new VectorLayer({
            source: straitSource
        });
    
        


     this.map = new Map({
            layers: [OSMBaseLayer, straitsLayer],
            target: 'ol-map',
            view: view,
        });
    
       // Popup showing the position the user clicked
        var container = document.getElementById('popup')!;
        var popup = new Overlay({
            element: container,
            autoPan: {
              animation: {
                duration: 250,
              },
            },
        });
        this.map.addOverlay(popup);

      /* Add a pointermove handler to the map to render the popup.*/
        this.map.on('pointermove',  (evt) => {
            var feature = this.map.forEachFeatureAtPixel(evt.pixel, function (feat, layer) {
                return feat;
            }
            );
    
            if (feature && feature.get('type') == 'Point') {
                var coordinate = evt.coordinate;    //default projection is EPSG:3857 you may want to use ol.proj.transform
    
                content.innerHTML = feature.get('desc');
                popup.setPosition(coordinate);
            }
            else {
                popup.setPosition(undefined);
                
            }
        });

    var data=[{"Lon":20.4678,"Lat":44.7953, "Name":'testC', "StreetName":"Vracar", "AverageRate":7},{"Lon":20.380088,"Lat":44.809956,"Name":'Centar 2', "StreetName":"Novi BG", "AverageRate":4},{"Lon":20.416665,"Lat": 44.7833302, "Name":'Centar 3', "StreetName":"Cukarica", "AverageRate":10},{"Lon":20.4833314,"Lat":44.7833302, "Name":'Centar', "StreetName":"Vozdovac", "AverageRate":5},];
    
    function addPointGeom(data:any) {
    
            data.forEach(function(item:any) { //iterate through array...
                var longitude = item.Lon,
                    latitude = item.Lat,
                    rate=item.AverageRate,
                    name =item.Name,
                    street=item.StreetName,
                    iconFeature = new Feature({
                        geometry: new Point(transform([longitude, latitude], 'EPSG:4326',
                            'EPSG:3857')),
                      type: 'Point',
                      //desc: 'Θέλει όπλο ο μικρός, θέλει να γίνει άντρας'}),
                        desc: '<pre> <b>Center Details </b> ' + '<br>' + 'Name : ' + name + '<br>StreetName: ' + street + '<br>AverageRate: ' +   rate +  '</pre>'}),
                    iconStyle = new Style({
                      image: new Icon({
                        anchor: [0.5, 46],
                        anchorXUnits: 'fraction',
                        anchorYUnits: 'pixels',
                        src:'https://docs.maptiler.com/openlayers/default-marker/marker-icon.png'
                      })
                    });
                iconFeature.setStyle(iconStyle);
    
                straitSource.addFeature(iconFeature);
            });
        }// End of function showStraits()
    
    addPointGeom(data);
    
    
    
     /*function addPointsGeom(data) {
            
            var straitFeatures = data.map(item => { //iterate through array...
                let longitude = item.Lon,
                    latitude = item.Lat,
                    iconFeature = new ol.Feature({
                        geometry: new ol.geom.Point(ol.proj.transform([longitude, latitude], 'EPSG:4326',
                            'EPSG:3857')),
                        
                        type: 'Point',
                        desc: '<pre>Strait   : ' + '<br>' + 'Latitude : ' + latitude + '<br>Longitude: ' + longitude + '</pre>',
                        id: 1
                    }),
                    iconStyle = new ol.style.Style({
                        image: new ol.style.Circle({
                            radius: 3,
                            stroke: new ol.style.Stroke({
                                color: 'blue'
                            }),
                            fill: new ol.style.Fill({
                                color: [57, 228, 193, 0.84]
                            }),
                        })
                    });
    
                iconFeature.setStyle(iconStyle);
    
                straitSource.addFeature(iconFeature);
              return iconFeature;
            });
        }*/
  }
  



/********** Simple Map ************
  MakeMap(){
    this.map = new Map({
      view: new View({
       center: fromLonLat([20.448921, 44.786568]),
        zoom: 11,
      }),
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      target: 'ol-map'
    });
  }
*/


  retrieveCentres(): void {
    this.centresService.getAll()
      .subscribe({
        next: (data) => {
          this.centres = new MatTableDataSource(<Centre[][]>data);
          this.centres.sort = this.sort;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }


  retrieveCentresByTime(): void {
    if(this.formTime != "" && this.formDate != "")
    {
      let time: appointmentSearchDTO = new appointmentSearchDTO();
      time.dateTime = this.formDate+"T"+this.formTime;
      this.centresService.getAllByTime(time)
        .subscribe({
          next: (data) => {

            this.centres = new MatTableDataSource(<Centre[][]>data);
            this.centres.sort = this.sort;
            console.log(data);
          },
          error: (e) => console.error(e)
        });
    }
  }
  setActiveCentre(centre: Centre, index: number): void {
    this.currentCentre = centre;
    this.currentIndex = index;
  }

  filterByNameAndSurname(): void {
    this.centresService.getAll()
      .subscribe({
        next: (data) => {
          this.centres = new MatTableDataSource(<any>data.filter(centres => centres.name?.includes(this.name) && centres.streetName?.includes(this.streetName)));
        },
        error: (e) => console.error(e)
      });
  }

}
