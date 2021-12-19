import React, {useState} from 'react';
import { Row, Col, TimePicker, InputNumber, Button, message } from "antd";
import moment from 'moment';
import Axios from 'axios';

const format = 'HH:mm';
const identifier = "calculator-client";

const PricePicker = ({addPrice, updateTimeline, priceMap, updateProfit}) => {

    const [timeString, setTimeString] = useState("");
    const [time, setTime] = useState(null);

    const [price, setPrice] = useState(0);

    const handleAddPrice = () => {
        if(!timeString || price == null) {
            message.info('Invalid time or price');
            return;
        } 
        if(!validTime()) {
            message.info('Trading time must be from 10:00 to 16:00');
            return;
        }
        addPrice(timeString, price);
    };

    const validTime = () => {
        if(time.hours() < 10 || time.hours() >=16 ) {
            return false;
        }
        return true;
    }

    const timeChange = (time, timeString) => {
        let yesterday = moment().add(-1, 'day').format("yyyy-MM-DD");
        let dateTime = `${yesterday} ${timeString}`;
        setTimeString(dateTime);
        setTime(time);
    };

    const priceChange = (value) => {
        setPrice(value);
    };

    const maxProfit = () => {
        if(!priceMap || priceMap.size === 0) {
            message.info('No price list');
            return;
        }

        let sortedList = sortTime();
        let startTIme = sortedList[0][0];
        let endTime = sortedList[sortedList.length - 1][0];
        let priceList = generatePriceList(sortedList, startTIme, endTime);
        Axios.post('http://localhost:8088/maxProfit', {
            identifier: identifier,
            startTime: startTIme,
            endTime: endTime,
            priceList: priceList
        })
        .then((response) => {
            updateProfit({
                maxProfit: response.data.maxProfit,
                buyValue: response.data.buyValue,
                sellValue: response.data.sellValue
            })
        })
        .catch((error) => {
            message.error(error.response.data.message);
        });
    };

    const sortTime = () => {
        return [...priceMap.entries()].sort();
    }

    const generatePriceList = (sortedList, startTime, endTime) => {
        let priceList = [];
        let startMonent = moment(startTime);
        let endMonent = moment(endTime);
        while(!startMonent.isAfter(endMonent)) {
            let time = startMonent.format("yyyy-MM-DD HH:mm");
            let price = priceMap.get(time);
            priceList.push(price ? price : null);
            startMonent.add(1, "m");
        }
        return priceList;
    }

    const resetAll = () => {
        setTimeString("");
        setTime(null);
        setPrice(0);
        updateTimeline(new Map());
        updateProfit({});
    };

    return (
        <>
            <Row align="center" justify="center" className="row">
                <Col span={8} className="col">
                    <TimePicker format={format} style={{width: "100%"}} onChange={timeChange} value={time} placeholder="Choose a time between 10:00 and 16:00"/>
                </Col>
                <Col span={8} className="col">
                <InputNumber addonBefore="$" style={{width: "100%"}} defaultValue={0} onChange={priceChange} value={price}/>

                </Col>
                <Col span={8} className="col">
                    <Button type="primary" style={{width: "100%"}} onClick={handleAddPrice}>Add Price</Button>
                </Col>
                
            </Row>
            <Row align="center" justify="center" className="row">
                <Col span={12} className="col">
                    <Button type="primary" style={{width: "100%"}} onClick={maxProfit}>Calculate Max Profit</Button>
                </Col>
                <Col span={12} className="col">
                    <Button type="secondary" style={{width: "100%"}} onClick={resetAll}>Reset</Button>
                </Col>
            </Row>
        </>
    );
}

export default PricePicker;