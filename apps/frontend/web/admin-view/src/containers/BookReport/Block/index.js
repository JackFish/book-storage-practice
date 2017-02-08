/**
 * Created by ksb on 16. 11. 16.
 */
import React, {PropTypes, Component} from 'react';
import {ButtonGroup, Button} from 'react-bootstrap';
import Draft from './Draft'
import Image from './Image'
import Youtube from './Youtube'
import {BLOCK_TYPE_DRAFT, BLOCK_TYPE_IMAGE, BLOCK_TYPE_YOUBUTE} from '../../../helpers/constants';

export default class Block extends Component {
    render() {
        const {index, block, blockState, setBlockState, removeBlock} = this.props;

        return (
            <div style={styles.root}>
                {
                    block.type && block.type.value==BLOCK_TYPE_DRAFT &&
                    <Draft index={index} block={block} blockState={blockState} setBlockState={setBlockState} />
                }
                {
                    block.type && block.type.value==BLOCK_TYPE_IMAGE &&
                    <Image index={index} block={block} blockState={blockState} setBlockState={setBlockState} />
                }
                {
                    block.type && block.type.value==BLOCK_TYPE_YOUBUTE &&
                    <Youtube index={index} block={block} blockState={blockState} setBlockState={setBlockState} />
                }

                <ButtonGroup vertical block>
                    <Button onClick={removeBlock}>삭제</Button>
                </ButtonGroup>
            </div>
        );
    }
}

const styles = {
    root: {
        padding: 10,
        width: "100%",
        border: "1px solid #ddd",
        marginBottom: "10px",
    },
};

